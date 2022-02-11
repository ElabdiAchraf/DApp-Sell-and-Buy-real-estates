package com.example.payment_service.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class PaymentContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b0319163390811782558152600360208190526040909120805460ff19166001830217905550600080546001600160a01b03168152600460205260409020805460ff191660011790556104d7806100736000396000f3fe60806040526004361061007b5760003560e01c80637b855bf01161004e5780637b855bf014610125578063d04fa3ff1461015d578063e35fe36614610170578063e8b5e51f146101b057600080fd5b806327e235e3146100805780635764727a146100c05780635d735587146100d357806374d5e100146100e8575b600080fd5b34801561008c57600080fd5b506100ad61009b3660046103ce565b60026020526000908152604090205481565b6040519081526020015b60405180910390f35b3480156100cc57600080fd5b50476100ad565b6100e66100e13660046103f2565b6101b8565b005b3480156100f457600080fd5b506101186101033660046103ce565b60036020526000908152604090205460ff1681565b6040516100b79190610434565b34801561013157600080fd5b50600054610145906001600160a01b031681565b6040516001600160a01b0390911681526020016100b7565b6100e661016b3660046103f2565b6102c1565b34801561017c57600080fd5b506101a061018b3660046103ce565b60046020526000908152604090205460ff1681565b60405190151581526020016100b7565b6100e6610390565b3360009081526003602081905260409091205460ff16818111156101de576101de61041e565b1480156101fa57503360009081526004602052604090205460ff165b61020357600080fd5b600061020f8247610472565b905060006001600160a01b0384166108fc61022a8447610472565b6040518115909202916000818181858888f16001600160a01b038916600090815260026020526040812080549297508996509450925061026c91508490610472565b909155508190506102bb5760405162461bcd60e51b81526020600482015260146024820152732330b4b632b2103a379039b2b7321022ba3432b960611b60448201526064015b60405180910390fd5b50505050565b3360009081526003602081905260409091205460ff16818111156102e7576102e761041e565b14801561030357503360009081526004602052604090205460ff165b61030c57600080fd5b60006103188247610472565b905060006001600160a01b0384166108fc6103338447610472565b6040518115909202916000818181858888f193505050509050806102bb5760405162461bcd60e51b81526020600482015260146024820152732330b4b632b2103a379039b2b7321022ba3432b960611b60448201526064016102b2565b33600090815260026020526040812080543492906103af908490610489565b9091555050565b6001600160a01b03811681146103cb57600080fd5b50565b6000602082840312156103e057600080fd5b81356103eb816103b6565b9392505050565b6000806040838503121561040557600080fd5b8235610410816103b6565b946020939093013593505050565b634e487b7160e01b600052602160045260246000fd5b602081016004831061045657634e487b7160e01b600052602160045260246000fd5b91905290565b634e487b7160e01b600052601160045260246000fd5b6000828210156104845761048461045c565b500390565b6000821982111561049c5761049c61045c565b50019056fea264697066735822122041528258f2fbad4be4fcfb368890fcbc881bc143768849081e0eb46d7352a17064736f6c634300080b0033";

    public static final String FUNC_BALACEOF = "balaceOf";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_CREATORADMIN = "creatorAdmin";

    public static final String FUNC_INVEST = "invest";

    public static final String FUNC_PAYMENTACCEPTED = "paymentAccepted";

    public static final String FUNC_PAYMENTDENIED = "paymentDenied";

    public static final String FUNC_USERROLES = "userRoles";

    public static final String FUNC_VERIFIEDUSERS = "verifiedUsers";

    @Deprecated
    protected PaymentContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PaymentContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PaymentContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PaymentContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> balaceOf() {
        final Function function = new Function(FUNC_BALACEOF, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> balances(String param0) {
        final Function function = new Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> creatorAdmin() {
        final Function function = new Function(FUNC_CREATORADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> invest(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_INVEST, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> paymentAccepted(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_PAYMENTACCEPTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> paymentDenied(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_PAYMENTDENIED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> userRoles(String param0) {
        final Function function = new Function(FUNC_USERROLES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> verifiedUsers(String param0) {
        final Function function = new Function(FUNC_VERIFIEDUSERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static PaymentContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PaymentContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PaymentContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PaymentContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PaymentContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PaymentContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PaymentContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PaymentContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PaymentContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<PaymentContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PaymentContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PaymentContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PaymentContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<PaymentContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PaymentContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
