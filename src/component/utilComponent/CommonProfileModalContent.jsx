import { faImage } from '@fortawesome/free-regular-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import PropTypes from 'prop-types';
import { useMemo, useState } from 'react';
import { Carousel } from 'react-bootstrap';
import { createRandomUser } from 'component/utility/FakeUser';
import { faGear } from '@fortawesome/free-solid-svg-icons';
import CarouselModal from './CarouselModal';

const CommonProfileModalContent = ({
  handleCloseModal,
  userInfo,
  stateContent,
  footerContent,
}) => {
  console.log('ProfileModalContent open ::', userInfo);

  const [showCarousel, setShowCarousel] = useState(false);

  const fakeUsers = useMemo(
    () => Array.from({ length: 1 }, () => createRandomUser()),
    []
  );

  const handleImageClick = () => {
    setShowCarousel(!showCarousel);
  };

  return (
    <>
      <div className="modal-header">
        <h5 className="modal-title">
          <FontAwesomeIcon
            icon={faImage}
            className="pointer"
            style={{ marginRight: '10px' }}
          />
          <FontAwesomeIcon icon={faGear} className="pointer" />
        </h5>
        <button
          type="button"
          className="btn-close"
          onClick={handleCloseModal}
          aria-label="hidden"
        />
      </div>
      <div
        className="modal-body"
        style={{ margin: 'auto', textAlign: 'center' }}
      >
        <div style={{ height: '55%' }}>test1</div>
        {userInfo?.avatar && (
          <img
            src={userInfo.avatar}
            alt="Selected Avatar"
            className="pointer"
            onClick={() => handleImageClick()}
            aria-hidden="true"
          />
        )}
        <div className="multi-profile-name">{userInfo.username}</div>
        <div>{stateContent}</div>
      </div>
      <div className="modal-footer" style={{ height: '18%' }}>
        {footerContent}
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
    </>
  );
};

export default CommonProfileModalContent;

/**
 * the reason why we should use TypeScript!!
 */
CommonProfileModalContent.propTypes = {
  handleCloseModal: PropTypes.func.isRequired,
  userInfo: PropTypes.shape({
    userId: PropTypes.string,
    username: PropTypes.string,
    email: PropTypes.string,
    avatar: PropTypes.string,
  }).isRequired,
  stateContent: PropTypes.string.isRequired,
  footerContent: PropTypes.string.isRequired,
};
