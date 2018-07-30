[![Build Status](https://travis-ci.org/authenteq/java-bigchaindb-driver.svg?branch=master)](https://travis-ci.org/authenteq/java-bigchaindb-driver)
[![Gitter](http://badges.gitter.im/bigchaindb/bigchaindb.svg)](https://gitter.im/bigchaindb/bigchaindb)
[![java-bigchaindb-driver](media/repo-banner@2x.png)](https://www.bigchaindb.com)

> Official Java driver for [BigchainDB](https://github.com/bigchaindb/bigchaindb) created by [Authenteq](https://authenteq.com).

**Please note**: due to non-official org.json implementation the driver is not compatible with Android

## Compatibility

| BigchainDB Server | BigchainDB Java Driver |
| ----------------- |------------------------------|
| `1.0`             | `0.1.x`                      |
| `2.0`             | `0.2.x`                      |


## Contents

* [Installation and Usage](#installation-and-usage)
* [Example: Create a transaction](#example-create-a-transaction)
* [Documentation](#bigchaindb-documentation)
* [Authors](#authors)
* [License](#license)

## Installation and Usage
The build system now is fully gradle-based, so to build the driver run:
```bash
./gradlew install
```
or use maven
```bash
mvn clean install
```

## Set up your configuration
```java
BigchainDbConfigBuilder
	.baseUrl("https://test.ipdb.io")
	.addToken("app_id", "2bbaf3ff")
	.addToken("app_key", "c929b708177dcc8b9d58180082029b8d").setup();
```

## Example: Prepare keys, assets and metadata
```java
//    prepare your keys
net.i2p.crypto.eddsa.KeyPairGenerator edDsaKpg = new net.i2p.crypto.eddsa.KeyPairGenerator();
KeyPair keyPair = edDsaKpg.generateKeyPair();

//    New asset
Map<String, String> assetData = new TreeMap<String, String>() {{
    put("city", "Berlin, DE");
    put("temperature", "22");
    put("datetime", new Date().toString());
}};

//    New metadata
MetaData metaData = new MetaData();
metaData.setMetaData("what", "My first BigchainDB transaction");
```

## Example: Create a Transaction
```java	
//    Set up your transaction
Transaction transaction = BigchainDbTransactionBuilder
	.init()
	.addAssets(assetData, TreeMap.class)
	.addMetaData(metaData)
	.operation(Operations.CREATE)
	.buildOnly((EdDSAPublicKey) keyPair.getPublic());
```

## Example: Create and Sign Transaction
```java
//    Set up your transaction
Transaction transaction = BigchainDbTransactionBuilder
	.init()
	.addAssets(assetData, TreeMap.class)
	.addMetaData(metaData)
	.operation(Operations.CREATE)
	.buildAndSignOnly((EdDSAPublicKey) keyPair.getPublic(), (EdDSAPrivateKey) keyPair.getPrivate());

```

## Example: Create, Sign and Send a Transaction
```java
//    Set up your transaction
Transaction transaction = BigchainDbTransactionBuilder
	.init()
	.addAssets(assetData, TreeMap.class)
	.addMetaData(metaData)
	.operation(Operations.CREATE)
	.buildAndSign((EdDSAPublicKey) keyPair.getPublic(), (EdDSAPrivateKey) keyPair.getPrivate())
	.sendTransaction();

```

## Example: Setup Config with Websocket Listener
```java
public class MyCustomMonitor implements MessageHandler {
	@Override
	public void handleMessage(String message) {
		ValidTransaction validTransaction = JsonUtils.fromJson(message, ValidTransaction.class);
	}
}

// config
BigchainDbConfigBuilder
	.baseUrl("https://test.ipdb.io")
	.addToken("app_id", "2bbaf3ff")
	.addToken("app_key", "c929b708177dcc8b9d58180082029b8d")
	.webSocketMonitor(new MyCustomMonitor())
	.setup();
```

<h2>Api Wrappers</h2>
<h3>Transactions</h3>

<h4>Send a Transaction</h4>

```java
TransactionsApi.sendTransaction(Transaction transaction) throws IOException
```

<h4>Send a Transaction with Callback</h4>

```java
TransactionsApi.sendTransaction(Transaction transaction, final GenericCallback callback) 
```

<h4>Get Transaction given a Transaction Id</h4>

```java
Transaction TransactionsApi.getTransactionById(String id) throws IOException
```

<h4>Get Transaction given an Asset Id</h4>

```java
Transactions TransactionsApi.getTransactionsByAssetId(String assetId, Operations operation)
```

<h3>Outputs</h3>

<h4>Get Outputs given a public key</h4>

```java
Outputs getOutputs(String publicKey) throws IOException
```

<h4>Get Spent Outputs given a public key</h4>

```java
Outputs getSpentOutputs(String publicKey) throws IOException
```

<h4>Get Unspent Outputs given a public key</h4>

```java
Outputs getUnspentOutputs(String publicKey) throws IOException
```

<h3>Assets</h3>

<h4>Get Assets given search key</h4>

```java
Assets getAssets(String searchKey) throws IOException
```

<h4>Get Assets given search key and limit</h4>

```java
Assets getAssetsWithLimit(String searchKey, String limit) throws IOException
```

<h3>Blocks</h3>

<h4>Get Blocks given block id</h4>

```java
Block getBlock(String blockId) throws IOException
```

<h4>Get Blocks given transaction id</h4>

```java
List<String> getBlocksByTransactionId(String transactionId) throws IOException
```

<h3>MetaData</h3>

<h4>Get MetaData given search key</h4>

```java
MetaDatas getMetaData(String searchKey) throws IOException
```

<h4>Get MetaData given search key and limit</h4>

```java
MetaDatas getMetaDataWithLimit(String searchKey, String limit) throws IOException
```

<h3>Validators</h3>

<h4>Gets the the local validators set of a given node</h4>

```java
Validators getValidators() throws IOException
```


## BigchainDB Documentation

- [HTTP API Reference](https://docs.bigchaindb.com/projects/server/en/latest/http-client-server-api.html)
- [The Transaction Model](https://docs.bigchaindb.com/projects/server/en/latest/metadata-models/transaction-model.html?highlight=crypto%20conditions)
- [Inputs and Outputs](https://docs.bigchaindb.com/projects/server/en/latest/metadata-models/inputs-outputs.html)
- [Asset Transfer](https://docs.bigchaindb.com/projects/py-driver/en/latest/usage.html#asset-transfer)
- [All BigchainDB Documentation](https://docs.bigchaindb.com/)

## Authors

- The [Authenteq](https://authenteq.com) team and others (see CONTRIBUTORS file).

## License

```
    java-bigchaindb-driver - Official Java driver for[BigchainDB
    Copyright (C) 2017  Authenteq

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
```
