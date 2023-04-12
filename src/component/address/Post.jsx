import React from 'react';
import PropTypes from 'prop-types';
import DaumPostcode from 'react-daum-postcode';
import '../../css/style.css';

const Post = ({ company, setcompany }) => {
  const complete = (data) => {
    let fullAddress = data.address;
    let extraAddress = '';

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        extraAddress +=
          extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }
    setcompany({
      ...company,
      address: fullAddress,
    });
  };

  return (
    <div>
      <DaumPostcode className="postmodal" autoClose onComplete={complete} />
    </div>
  );
};

Post.propTypes = {
  company: PropTypes.shape({
    address: PropTypes.string,
  }).isRequired,
  setcompany: PropTypes.func.isRequired,
};

export default Post;
