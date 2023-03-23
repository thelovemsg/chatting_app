import { Form } from "react-bootstrap";
import { useState } from "react";
import axios from "axios";
import {
  StyledButton,
  StyledForm,
  StyledGroup,
  StyledLabel,
  StyledMsgLabel,
  StyledValidationCheck,
} from "../styled-components/StyledForm";
import Post from "./address/Post";
import { useDispatch } from "react-redux";
import { REGISTER_REQUEST } from "../reducers/user";

const Register = () => {
  let duplicateCheckFlag = true;
  const dispatch = useDispatch();
  const [enroll_company, setEnroll_company] = useState({
    address: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    if (duplicateCheckFlag) {
      alert("plz check duplicate values");
      return;
    }

    const data = {
      email: e.target.email.value,
      name: e.target.name.value,
      nickname: e.target.nickname.value,
      password: e.target.password.value,
      passwordCheck: e.target.passwordCheck.value,
      phoneNumber: e.target.phoneNumber.value,
      address1: e.target.address1.value,
      address2: e.target.address2.value,
    };

    dispatch(REGISTER_REQUEST(data));
  };

  const [popup, setPopup] = useState(false);

  const [emailValidationMsg, setEmailValidationMsg] = useState("");
  const [nicknameValidationMsg, setNicknameValidationMsg] = useState("");
  const [phoneNumberValidationMsg, setPhoneNumberValidationMsg] = useState("");

  const isValidEmail = (email) => {
    const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
    return emailRegex.test(email);
  };

  const autoHyphen = (phoneNumber) => {
    console.log(phoneNumber);
    //   target.value = target.value
    //  .replace(/[^0-9]/g, '')
    // .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
  };

  const handleValidationBlur = async (e) => {
    const name = e.target.name;
    const value = e.target.value;
    if (name === "email" && !isValidEmail(value)) {
      setEmailValidationMsg("Invalid email address");
      return;
    } else {
      setEmailValidationMsg("");
    }

    const response = await axios.post(
      "http://localhost:9090/findMemberByTarget",
      { name, value }
    );

    let result = response?.data;
    result ? (duplicateCheckFlag = false) : (duplicateCheckFlag = true);
    if (result === "email") {
      setEmailValidationMsg(`${response.data} already exist`);
    } else if (result === "phoneNumber") {
      setPhoneNumberValidationMsg(`${response.data} already exist`);
    } else if (result === "nickname") {
      setNicknameValidationMsg(`${response.data} already exist`);
    } else {
      if (name === "email") {
        setEmailValidationMsg("");
      } else if (name === "phoneNumber") {
        setPhoneNumberValidationMsg("");
      } else if (name === "nickname") {
        setNicknameValidationMsg("");
      }
    }
  };
  const handleComplete = (data) => {
    setPopup(!popup);
  };

  return (
    <StyledForm onSubmit={handleSubmit}>
      <StyledGroup className="mb-3">
        <StyledLabel>Email address</StyledLabel>
        {emailValidationMsg ? (
          <StyledMsgLabel>{emailValidationMsg}</StyledMsgLabel>
        ) : (
          ""
        )}
        <Form.Control
          type="email"
          name="email"
          placeholder="Enter email"
          required
          onBlur={handleValidationBlur}
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Name</StyledLabel>
        <Form.Control type="text" name="name" placeholder="name" required />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Nickname</StyledLabel>
        {nicknameValidationMsg ? (
          <StyledMsgLabel>{nicknameValidationMsg}</StyledMsgLabel>
        ) : (
          ""
        )}
        <Form.Control
          type="text"
          name="nickname"
          placeholder="nickname"
          onBlur={handleValidationBlur}
          required
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Password</StyledLabel>
        <Form.Control
          type="password"
          name="password"
          placeholder="Password"
          required
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Password Check</StyledLabel>
        <Form.Control
          type="password"
          name="passwordCheck"
          placeholder="Password again"
          required
        />
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Phone Number</StyledLabel>
        {phoneNumberValidationMsg ? (
          <StyledMsgLabel>{phoneNumberValidationMsg}</StyledMsgLabel>
        ) : (
          ""
        )}
        <Form.Control
          type="text"
          name="phoneNumber"
          placeholder="phone number"
          onChange={autoHyphen}
          onBlur={handleValidationBlur}
          required
        />
      </StyledGroup>
      <StyledGroup>
        <StyledLabel>Address 1</StyledLabel>
        <Form.Control
          className="mb-3 w-40"
          type="text"
          placeholder="Addess"
          name="address1"
          value={enroll_company.address}
          required={true}
          readOnly
          onClick={handleComplete}
        />
        {popup && (
          <Post company={enroll_company} setcompany={setEnroll_company}></Post>
        )}
      </StyledGroup>
      <StyledGroup className="mb-3">
        <StyledLabel>Address 2</StyledLabel>
        <Form.Control
          type="text"
          name="address2"
          placeholder="detailed address"
          required
        />
      </StyledGroup>
      <StyledValidationCheck>
        가입시 뭐 없으면 메시지 표시
      </StyledValidationCheck>
      <StyledGroup className="justify-content-center">
        <StyledButton variant="primary" type="submit">
          Try Signin!
        </StyledButton>
        <StyledButton variant="secondary" type="submit">
          Find Password
        </StyledButton>
      </StyledGroup>
    </StyledForm>
  );
};

export default Register;
