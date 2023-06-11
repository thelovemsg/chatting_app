import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PropTypes from 'prop-types';
import { useState, useRef, useEffect } from 'react';
import { createRandomUuid } from 'component/utility/FakeUser';
import { faCamera, faUser } from '@fortawesome/free-solid-svg-icons';
import { Trans, useTranslation } from 'react-i18next';
import { useDispatch, useSelector } from 'react-redux';
import {
  ADD_USER_MULTI_PROFILE_INFO_REQUEST,
  SET_USER_MULTI_PROFILE_STATUS_FALSE_REQUEST,
} from '../../reducers/user/userMultiProfile';

const NewMultiProfileModalContent = ({ handleCloseModal }) => {
  // const { id } = useSelector((state) => state.user);
  const { t } = useTranslation();
  const { success } = useSelector((state) => state.user.multiProfile);
  const [nameInput, setNameInput] = useState('');
  const [descriptionInput, setDescriptionInput] = useState('');
  const [uploadedImage, setUploadedImage] = useState(null);

  const dispatch = useDispatch();

  useEffect(() => {
    if (success) {
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
    if (nameInput.trim() === '' || nameInput === null) {
      alert('Please enter a name for the profile');
      return;
    }

    if (descriptionInput.trim() === '' || descriptionInput === null) {
      alert('Please enter a description for the profile');
      return;
    }
    const data = {
      name: nameInput.trim(),
      description: descriptionInput.trim(),
      avatar: uploadedImage,
      id: createRandomUuid(),
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
                placeholder={t('default.name')}
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
                placeholder={t('default.description')}
              />
              <div className="profile-modal-input-counter">{`${descriptionInput.length}/20`}</div>
            </div>
          </div>
        </div>
        <div className="multi-profile-info">
          <Trans i18nKey="friend.multiProfile.addGuide" />
        </div>
        <div className="multi-profile-btns">
          <button
            type="button"
            className="multi-profile-btn check"
            onClick={addMultiProfile}
          >
            <Trans i18nKey="default.check" />
          </button>
          <button
            type="button"
            className="multi-profile-btn"
            onClick={handleCloseModal}
          >
            <Trans i18nKey="default.cancel" />
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
