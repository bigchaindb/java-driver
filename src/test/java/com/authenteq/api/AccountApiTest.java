package com.authenteq.api;

import com.authenteq.model.Account;
import com.authenteq.model.Assets;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyPair;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertTrue;

/**
 * The Class AccountApiTest.
 */
public class AccountApiTest extends AbstractApiTest {

    private static final String publicKey = "302a300506032b657003210033c43dc2180936a2a9138a05f06c892d2fb1cfda4562cbc35373bf13cd8ed373";
    private static final String privateKey = "302e020100300506032b6570042204206f6b0cd095f1e83fc5f08bffb79c7c8a30e77a3ab65f4bc659026b76394fcea8";

    /**
     * Test asset search.
     */
    @Test
    public void testAssetSearch() {
        try {
            Account account = AccountApi.loadAccount(publicKey, privateKey);
            assertTrue(account.getPublicKey() != null);
            assertTrue(account.getPrivateKey() != null);
        } catch (InvalidKeySpecException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * Test create account.
     */
    @Test
    public void testCreateAccount() {
        Account account = AccountApi.createAccount();
        assertTrue(account.getPublicKey() != null);
        assertTrue(account.getPrivateKey() != null);
    }
}
