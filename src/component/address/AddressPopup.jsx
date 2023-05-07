// AddressPopup.js
import React, { useState } from 'react';
import PropTypes from 'prop-types'; // Import PropTypes
import { StyledInputForm } from 'styled-components/StyledForm';
import Post from './Post';

const AddressPopup = ({ enrollCompany, setEnrollCompany }) => {
  const [popup, setPopup] = useState(false);
  const handleComplete = () => {
    setPopup(!popup);
  };
  const { address } = enrollCompany;
  return (
    <>
      <StyledInputForm
        className="mb-3 w-40"
        type="text"
        placeholder="Address"
        name="address1"
        value={address}
        required
        readOnly
        onClick={handleComplete}
      />
      {popup && <Post company={enrollCompany} setcompany={setEnrollCompany} />}
    </>
  );
};

// Add PropTypes validation
AddressPopup.propTypes = {
  enrollCompany: PropTypes.shape({
    address: PropTypes.string.isRequired,
  }).isRequired,
  setEnrollCompany: PropTypes.func.isRequired,
};

export default AddressPopup;
