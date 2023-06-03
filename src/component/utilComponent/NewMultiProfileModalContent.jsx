import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PropTypes from 'prop-types';
import { useMemo, useRef, useState } from 'react';
import { Carousel } from 'react-bootstrap';
import { createRandomUser } from 'component/utility/FakeUser';
import { faCamera, faUser } from '@fortawesome/free-solid-svg-icons';
import CarouselModal from './CarouselModal';

const NewMultiProfileModalContent = ({ handleCloseModal, userInfo }) => {
  const [showCarousel, setShowCarousel] = useState(false);
  const [nameInput, setNameInput] = useState('');
  const [descriptionInput, setDescriptionInput] = useState('');
  const imageInput = useRef(null);

  const fakeUsers = useMemo(
    () => Array.from({ length: 1 }, () => createRandomUser()),
    []
  );

  const addMultiProfile = () => {
    console.log('add!');
    console.log('nameInput ::', nameInput);
    console.log('descriptionInput ::', descriptionInput);

    // add in RDBMS
  };

  const handleImageClick = () => {
    imageInput.current.click();
    setShowCarousel(!showCarousel);
  };

  const handleNameInput = (event) => {
    setNameInput(event.target.value);
  };

  const handleDescriptionInput = (event) => {
    setDescriptionInput(event.target.value);
  };

  const handleProfileImgUpload = (event) => {
    console.log(event.target.files[0]);
    // You now have access to the file object
    // You can add your image processing logic here
    /*
      Process
      1. call API
      2. add Multi profile
      3. show multi profile in multi profile section.
    */
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
      <div className="multi-profile-title">멀티프로필 만들기</div>
      <div className="modal-body">
        <div style={{ height: '40%', margin: 'auto' }}>
          <FontAwesomeIcon icon={faUser} className="multi-profile-set-image" />
          <label htmlFor="fileInput">
            <FontAwesomeIcon
              icon={faCamera}
              className="multi-profile-camera-image"
            />
          </label>
          <input
            id="fileInput"
            type="file"
            accept="image/jpg,impge/png,image/jpeg,image/gif"
            style={{ display: 'none' }}
            onChange={handleProfileImgUpload}
            ref={imageInput}
          />
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
          ID검색은 기본프로필 ID로만 가능하며, 멀티프로필 지정친구는 내 ID검색
          시에 해당 멀티프로필을 보게 됩니다.
        </div>
        <div style={{ float: 'right', marginTop: '75px' }}>
          <button
            type="button"
            className="multi-profile-true-btn"
            onClick={addMultiProfile}
          >
            test1
          </button>
          <button
            type="button"
            className="cancel-btn"
            onClick={handleCloseModal}
          >
            test2
          </button>
        </div>
      </div>
      {showCarousel && (
        <CarouselModal show={showCarousel} onHide={handleImageClick}>
          {fakeUsers.map((user, index) => (
            <Carousel.Item key={user.userId}>
              <img
                className="d-block w-100"
                src={user.avatar}
                alt={`Slide ${index + 1}`}
                style={{ height: '300px', width: 'auto' }} // Add this line
              />
              <Carousel.Caption>
                <h3>{`Slide ${index + 1} label`}</h3>
                <p>{userInfo.username}</p>
              </Carousel.Caption>
            </Carousel.Item>
          ))}
        </CarouselModal>
      )}
    </div>
  );
};

export default NewMultiProfileModalContent;

/**
 * the reason why we should use TypeScript!!
 */
NewMultiProfileModalContent.propTypes = {
  handleCloseModal: PropTypes.func.isRequired,
  userInfo: PropTypes.shape({
    userId: PropTypes.string,
    username: PropTypes.string,
    email: PropTypes.string,
    avatar: PropTypes.string,
  }).isRequired,
};
