import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PropTypes from 'prop-types';
import { useState, useRef, useMemo, useEffect } from 'react';
import { createRandomUser } from 'component/utility/FakeUser';
import { faCamera, faUser } from '@fortawesome/free-solid-svg-icons';
import { Trans } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import {
  ADD_USER_MULTI_PROFILE_INFO_REQUEST,
  SET_USER_MULTI_PROFILE_STATUS_FALSE_REQUEST,
} from '../../reducers/user';

const NewMultiProfileModalContent = ({ handleCloseModal }) => {
  // const { id } = useSelector((state) => state.user);
  const { success } = useSelector((state) => state.user.multiProfile);
  const [nameInput, setNameInput] = useState('');
  const [descriptionInput, setDescriptionInput] = useState('');
  const [uploadedImage, setUploadedImage] = useState(null);

  const dispatch = useDispatch();

  const fakeUsers = useMemo(
    () => Array.from({ length: 1 }, () => createRandomUser()),
    []
  );

  useEffect(() => {
    console.log('success :: ', success);
    if (success) {
      console.log('이거 닫혀야하는데?');
      handleCloseModal();
      dispatch(SET_USER_MULTI_PROFILE_STATUS_FALSE_REQUEST());
    }
  }, [success]);

  const fileInput = useRef(null);

  const handleFileUpload = (event) => {
    const file = event.target.files[0];
    if (!file) return;
    const uploadedImageUrl = URL.createObjectURL(file);
    setUploadedImage(uploadedImageUrl);
  };

  const handleImageClick = () => {
    fileInput.current.click();
  };

  const handleNameInput = (event) => {
    setNameInput(event.target.value);
  };

  const handleDescriptionInput = (event) => {
    setDescriptionInput(event.target.value);
  };

  const addMultiProfile = () => {
    const data = {
      name: nameInput,
      description: descriptionInput,
      image: uploadedImage,
      userId: fakeUsers[0].userId,
    };
    dispatch(ADD_USER_MULTI_PROFILE_INFO_REQUEST(data));
  };

  return (
    <div className="multi-profile-screen">
      <div className="modal-header">
        <button
          type="button"
          className="btn-close"
          onClick={handleCloseModal}
          aria-label="hidden"
        />
      </div>
      <div className="multi-profile-title">
        <Trans i18nKey="friend.multiProfile.title" />
      </div>
      <div className="modal-body">
        <div style={{ height: '40%', margin: 'auto' }}>
          <label htmlFor="fileInput">
            {uploadedImage ? (
              <img
                src={uploadedImage}
                alt="Profile"
                className="multi-profile-uploaded-image"
              />
            ) : (
              <FontAwesomeIcon
                icon={faUser}
                className="multi-profile-set-image"
              />
            )}
            <input
              type="file"
              id="fileInput"
              style={{ display: 'none' }}
              onChange={handleFileUpload}
              ref={fileInput}
            />
          </label>
          {uploadedImage ? (
            <FontAwesomeIcon
              icon={faCamera}
              className="multi-profile-added-camera-image"
              onClick={handleImageClick}
            />
          ) : (
            <FontAwesomeIcon
              icon={faCamera}
              className="multi-profile-camera-image"
              onClick={handleImageClick}
            />
          )}
        </div>
        <div style={{ width: '100%' }}>
          <div className="profile-modal-input-box">
            <div className="input-container">
              <input
                type="text"
                className="profile-modal-input-content"
                maxLength="20"
                value={nameInput}
                onChange={handleNameInput}
                placeholder="이름"
              />
              <div className="profile-modal-input-counter">{`${nameInput.length}/20`}</div>
            </div>
          </div>
          <div className="profile-modal-input-box">
            <div className="input-container">
              <input
                type="text"
                className="profile-modal-input-content"
                maxLength="20"
                value={descriptionInput}
                onChange={handleDescriptionInput}
                placeholder="상태메시지"
              />
              <div className="profile-modal-input-counter">{`${descriptionInput.length}/20`}</div>
            </div>
          </div>
        </div>
        <div className="multi-profile-info">
          <Trans i18nKey="friend.multiProfile.addGuide" />
        </div>
        <div style={{ float: 'right', marginTop: '85px' }}>
          <button
            type="button"
            className="multi-profile-add-btn"
            onClick={addMultiProfile}
          >
            test1
          </button>
          <button
            type="button"
            className="multi-profile-close-btn"
            onClick={handleCloseModal}
          >
            test2
          </button>
        </div>
      </div>
    </div>
  );
};

export default NewMultiProfileModalContent;

/**
 * the reason why we should use TypeScript!!
 */
NewMultiProfileModalContent.propTypes = {
  handleCloseModal: PropTypes.func.isRequired,
};