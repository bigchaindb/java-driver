package com.authenteq.api;

import com.authenteq.AbstractTest;
import com.authenteq.builders.BigchainDbConfigBuilder;
import com.authenteq.model.Account;
import com.authenteq.model.ApiEndpoints;
import com.authenteq.model.BigChainDBGlobals;
import com.authenteq.util.JsonUtils;
import com.authenteq.ws.MessageHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.authenteq.api.AssetsApiTest.V1_ASSET_JSON;
import static com.authenteq.api.AssetsApiTest.V1_ASSET_LIMIT_JSON;
import static com.authenteq.api.BlocksApiTest.V1_BLOCK_BY_TRANS_JSON;
import static com.authenteq.api.BlocksApiTest.V1_BLOCK_JSON;
import static com.authenteq.api.MetaDataApiTest.V1_METADATA_JSON;
import static com.authenteq.api.MetaDataApiTest.V1_METADATA_LIMIT_JSON;
import static com.authenteq.api.OutputsApiTest.*;
import static com.authenteq.api.TransactionApiTest.*;
import static com.authenteq.api.ValidatorsApiTest.V1_VALIDATORS_JSON;
import static net.jadler.Jadler.*;
import static org.junit.Assert.assertTrue;

public class AbstractApiTest extends AbstractTest {

    public static String V1_JSON = "{\n" +
            "  \"assets\": \"/assets/\",\n" +
            "  \"docs\": \"https://docs.bigchaindb.com/projects/server/en/v2.0.0b2/http-client-server-api.html\",\n" +
            "  \"metadata\": \"/metadata/\",\n" +
            "  \"outputs\": \"/outputs/\",\n" +
            "  \"streams\": \"ws://localhost:9985/api/v1/streams/valid_transactions\",\n" +
            "  \"transactions\": \"/transactions/\",\n" +
            "  \"validators\": \"/validators\"\n" +
            "}";

    /**
     * Inits the.
     */
    @BeforeClass
    public static void init() {
        initJadler();
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1")
                .respond()
                .withBody(V1_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/validators")
                .respond()
                .withBody(V1_VALIDATORS_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/assets/")
                .havingParameterEqualTo("search", "bigchaindb")
                .respond()
                .withBody(V1_ASSET_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/assets/")
                .havingParameterEqualTo("search", "bigchaindb")
                .havingParameterEqualTo("limit", "2")
                .respond()
                .withBody(V1_ASSET_LIMIT_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/metadata/")
                .havingParameterEqualTo("search", "bigchaindb")
                .respond()
                .withBody(V1_METADATA_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/metadata/")
                .havingParameterEqualTo("search", "bigchaindb")
                .havingParameterEqualTo("limit", "2")
                .respond()
                .withBody(V1_METADATA_LIMIT_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/blocks/1")
                .respond()
                .withBody(V1_BLOCK_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/blocks")
                .havingParameterEqualTo("transaction_id", "4957744b3ac54434b8270f2c854cc1040228c82ea4e72d66d2887a4d3e30b317")
                .respond()
                .withBody(V1_BLOCK_BY_TRANS_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/outputs")
                .havingParameterEqualTo("public_key", PUBKEY)
                .respond()
                .withBody(V1_OUTPUTS_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/outputs")
                .havingParameterEqualTo("public_key", PUBKEY)
                .havingParameterEqualTo("spent", "true")
                .respond()
                .withBody(V1_OUTPUTS_SPENT_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/outputs")
                .havingParameterEqualTo("public_key", PUBKEY)
                .havingParameterEqualTo("spent", "false")
                .respond()
                .withBody(V1_OUTPUTS_UNSPENT_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/transactions/" + TRANSACTION_ID)
                .respond()
                .withBody(V1_GET_TRANSACTION_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("GET")
                .havingPathEqualTo("/api/v1/transactions")
                .havingParameterEqualTo("asset_id", TRANSACTION_ID)
                .havingParameterEqualTo("operation", "TRANSFER")
                .respond()
                .withBody(V1_GET_TRANSACTION_BY_ASSETS_JSON)
                .withStatus(200);
        onRequest()
                .havingMethodEqualTo("POST")
                .havingPathEqualTo("/api/v1/transactions")
                .respond()
                .withBody(V1_POST_TRANSACTION_JSON)
                .withStatus(200);
        JsonUtils.setJsonDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        BigchainDbConfigBuilder
                .baseUrl("http://localhost:" + port())
                .addToken("app_id", "2bbaf3ff")
                .addToken("app_key", "c929b708177dcc8b9d58180082029b8d")
                .webSocketMonitor(new MessageHandler() {
                    @Override
                    public void handleMessage(String message) {
                    }
                })
                .setup();

    }

    @AfterClass
    public static void tearDown() {
        closeJadler();
    }

    /**
     * Test db globals.
     */
    @Test
    public void testBigChainDBGlobals() {
        assertTrue(BigChainDBGlobals.getWsSocketUrl().endsWith("/api/v1/streams/valid_transactions"));
        assertTrue(BigChainDBGlobals.getAuthorizationTokens().get("app_id").equals("2bbaf3ff"));
        assertTrue(BigChainDBGlobals.getAuthorizationTokens().get("app_key").equals("c929b708177dcc8b9d58180082029b8d"));
    }

    /**
     * Test api endpoints.
     */
    @Test
    public void testApiEndpoints() {
        ApiEndpoints api = BigChainDBGlobals.getApiEndpoints();
        assertTrue(api.getAssets().equals("/assets/"));
        assertTrue(api.getDocs().equals("https://docs.bigchaindb.com/projects/server/en/v2.0.0b2/http-client-server-api.html"));
        assertTrue(api.getMetadata().equals("/metadata/"));
        assertTrue(api.getOutputs().equals("/outputs/"));
        assertTrue(api.getStreams().equals("ws://localhost:9985/api/v1/streams/valid_transactions"));
        assertTrue(api.getTransactions().equals("/transactions/"));
        assertTrue(api.getValidators().equals("/validators"));
    }
}
