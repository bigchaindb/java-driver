package com.authenteq.api;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;

import com.authenteq.AbstractTest;
import com.authenteq.model.Outputs;
import org.junit.BeforeClass;
import org.junit.Test;

import com.authenteq.api.AssetsApi;
import com.authenteq.builders.BigchainDbConfigBuilder;
import com.authenteq.model.Account;
import com.authenteq.model.Output;
import com.authenteq.util.DriverUtils;
import com.authenteq.util.JsonUtils;
import com.authenteq.util.KeyPairUtils;

import net.i2p.crypto.eddsa.EdDSAPublicKey;


/**
 * The Class OutputsApiTest.
 */
public class OutputsApiTest extends AbstractApiTest {

    public static String PUBKEY = "1AAAbbb...ccc";

    public static String V1_OUTPUTS_JSON = "[\n" +
            "  {\n" +
            "    \"output_index\": 0,\n" +
            "    \"transaction_id\": \"2d431073e1477f3073a4693ac7ff9be5634751de1b8abaa1f4e19548ef0b4b0e\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"output_index\": 1,\n" +
            "    \"transaction_id\": \"2d431073e1477f3073a4693ac7ff9be5634751de1b8abaa1f4e19548ef0b4b0e\"\n" +
            "  }\n" +
            "]";

    public static String V1_OUTPUTS_SPENT_JSON = "[\n" +
            "  {\n" +
            "    \"output_index\": 0,\n" +
            "    \"transaction_id\": \"2d431073e1477f3073a4693ac7ff9be5634751de1b8abaa1f4e19548ef0b4b0e\"\n" +
            "  }\n" +
            "]";

    public static String V1_OUTPUTS_UNSPENT_JSON = "[\n" +
            "  {\n" +
            "    \"output_index\": 1,\n" +
            "    \"transaction_id\": \"2d431073e1477f3073a4693ac7ff9be5634751de1b8abaa1f4e19548ef0b4b0e\"\n" +
            "  }\n" +
            "]";

    /**
     * Test get outputs.
     */
    @Test
    public void testGetOutputs() {
        try {
            Outputs outputs = OutputsApi.getOutputs(PUBKEY);
            assertTrue(outputs.getOutput().size() == 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test get spent outputs.
     */
    @Test
    public void testGetSpentOutputs() {
        try {
            Outputs outputs = OutputsApi.getSpentOutputs(PUBKEY);
            assertTrue(outputs.getOutput().size() == 1);
            assertTrue(outputs.getOutput().get(0).getOutputIndex().equals("0"));
            assertTrue(outputs.getOutput().get(0).getTransactionId().equals("2d431073e1477f3073a4693ac7ff9be5634751de1b8abaa1f4e19548ef0b4b0e"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test get unspent outputs.
     */
    @Test
    public void testGetUnspentOutputs() {
        try {
            Outputs outputs = OutputsApi.getUnspentOutputs(PUBKEY);
            assertTrue(outputs.getOutput().size() == 1);
            assertTrue(outputs.getOutput().get(0).getOutputIndex().equals("1"));
            assertTrue(outputs.getOutput().get(0).getTransactionId().equals("2d431073e1477f3073a4693ac7ff9be5634751de1b8abaa1f4e19548ef0b4b0e"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
