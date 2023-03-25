// AddressPopup.js
import React, { useState } from "react";
import { Form } from "react-bootstrap";
import Post from "./Post";

const AddressPopup = ({ enroll_company, setEnroll_company }) => {
  const [popup, setPopup] = useState(false);

  const handleComplete = () => {
    setPopup(!popup);
  };

  return (
    <>
      <Form.Control
        className="mb-3 w-40"
        type="text"
        placeholder="Address"
        name="address1"
        value={enroll_company.address}
        required={true}
        readOnly
        onClick={handleComplete}
      />
      {popup && (
        <Post company={enroll_company} setcompany={setEnroll_company}></Post>
      )}
    </>
  );
};

export default AddressPopup;
