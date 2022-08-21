pragma solidity ^0.8.0;

pragma solidity >=0.7.0 <0.9.0;
contract Payment {
    address public creatorAdmin;
    enum Role {Visitor, User, Admin, SuperAdmin}
    address payable[] recipients;
    mapping(address=> uint) public balances;
    mapping(address => Role) public userRoles; // Keeps track of user roles
    mapping(address => bool) public verifiedUsers; // Keeps track of verified user, userId -> verified (true / false)


    // Initializing the Contract.
    constructor() public {
        creatorAdmin = msg.sender;
        userRoles[creatorAdmin] = Role.SuperAdmin;
        verifiedUsers[creatorAdmin] = true;
    }


    modifier verifiedSuperAdmin() {
        require(
            userRoles[msg.sender] == Role.SuperAdmin &&
            verifiedUsers[msg.sender]
        );
        _;
    }

    function invest() external payable {
        balances[msg.sender]+=msg.value;
    }

    function balaceOf()external view returns(uint) {
        return address(this).balance;
    }

    function paymentAccepted(address payable _to ,uint _value) public payable verifiedSuperAdmin {
        // Send returns a boolean value indicating success or failure.
        // This function is not recommended for sending Ether.
        uint a=address(this).balance-_value;
        bool sent = _to.send(address(this).balance-a);
        require(sent, "Failed to send Ether");
    }


    function paymentDenied(address payable _to,uint _value) public payable verifiedSuperAdmin {
        // Send returns a boolean value indicating success or failure.
        // This function is not recommended for sending Ether.
        uint a=address(this).balance-_value;
        bool sent = _to.send(address(this).balance-a);
        balances[_to]-=_value;
        require(sent, "Failed to send Ether");
    }

}


