import { useEffect, useRef, useState } from 'react';
import PropTypes from 'prop-types';
import { useDispatch, useSelector } from 'react-redux';
import { GET_TARGET_USER_PROFILE_IMAGE_LIST_REQUEST } from 'reducers/user/userInfoSetting';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faAngleLeft,
  faAngleRight,
  faArrowsDownToLine,
  faMinus,
  faPlus,
  faRotate,
  faEllipsis,
} from '@fortawesome/free-solid-svg-icons';

const ProfileImageCarouselModal = ({ handleCloseModal, targetUserId }) => {
  const [rotation, setRotation] = useState(0);
  const [scale, setScale] = useState(1); // Initial scale is 100% (or normal size)
  const { targetImageList } = useSelector((state) => state.user.info);

  const imageLength = targetImageList.length;
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(GET_TARGET_USER_PROFILE_IMAGE_LIST_REQUEST(targetUserId));
  }, []);

  const handleShrink = () => {
    if (scale === 1) return;
    setScale((prevScale) => Math.max(0.1, prevScale - 0.1)); // Decreases the scale, but not less than 10%
  };

  const handleMagnify = () => {
    setScale((prevScale) => Math.min(2, prevScale + 0.1)); // Increases the scale, but not more than 200%
  };

  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const imageRef = useRef([]);

  const onSelectImage = (currentIndex) => {
    setCurrentImageIndex(currentIndex.currentIndex);
    const currentImage = imageRef.current[currentIndex.currentIndex];
    currentImage?.scrollIntoView({
      inline: 'center',
      block: 'nearest',
    });
  };

  const handleImageMove = (currentIndex) => {
    const firstIndex = 0;
    const lastIndex = targetImageList.length - 1;

    if (currentIndex < firstIndex) {
      onSelectImage({ currentIndex: lastIndex });
      return;
    }
    if (currentIndex > lastIndex) {
      onSelectImage({ currentIndex: firstIndex });
      return;
    }
    onSelectImage({ currentIndex });
    setRotation(0);
    setScale(1);
  };

  const handleImageRotation = () => {
    const nextRotation = (rotation + 90) % 360;
    setRotation(nextRotation);
  };

  return (
    <>
      <div className="modal-header">
        <div className="custom-ml-10" style={{ fontSize: '13px' }}>
          {currentImageIndex + 1}/{imageLength}
        </div>
        <button
          type="button"
          className="btn-close"
          onClick={handleCloseModal}
          aria-label="hidden"
        />
      </div>
      <div className="modal-body">
        <div className="profile-carousel-wrapper">
          {currentImageIndex !== 0 ? (
            <FontAwesomeIcon
              className="profile-carousel-image-icon"
              icon={faAngleLeft}
              onClick={() => handleImageMove(currentImageIndex - 1)}
            />
          ) : (
            <FontAwesomeIcon
              className="profile-carousel-image-icon icon-blur"
              icon={faAngleLeft}
            />
          )}

          <div className="profile-image-carousel-box">
            {targetImageList?.length === 0 ? (
              <div>nothing</div>
            ) : (
              targetImageList.map((user, index) => {
                if (index === currentImageIndex) {
                  return (
                    <div
                      ref={(el) => {
                        imageRef.current[index] = el;
                      }}
                    >
                      <img
                        className="profile-image-carousel"
                        alt={`user profile ${index + 1}`}
                        src={user.avatar}
                        style={{
                          transform: `scale(${scale}) rotate(${rotation}deg)`,
                        }} // Apply both scale and rotation
                      />
                    </div>
                  );
                }
                return null;
              })
            )}
          </div>
          {currentImageIndex !== targetImageList.length - 1 ? (
            <FontAwesomeIcon
              className="profile-carousel-image-icon"
              icon={faAngleRight}
              onClick={() => handleImageMove(currentImageIndex + 1)}
            />
          ) : (
            <FontAwesomeIcon
              className="profile-carousel-image-icon icon-blur"
              icon={faAngleRight}
            />
          )}
        </div>
        <div className="profile-image-carousel-options">
          <div>
            <FontAwesomeIcon
              icon={faMinus}
              className={`profile-image-icon-option ${
                scale === 1 ? 'icon-blur' : ''
              }`}
              onClick={handleShrink}
            />
            <FontAwesomeIcon
              icon={faPlus}
              className="profile-image-icon-option"
              onClick={handleMagnify}
            />
            <FontAwesomeIcon
              icon={faRotate}
              className="profile-image-icon-option"
              onClick={() => {
                handleImageRotation();
              }}
            />
          </div>
          <div>
            <FontAwesomeIcon
              icon={faArrowsDownToLine}
              className="profile-image-icon-option"
            />
            <FontAwesomeIcon
              icon={faEllipsis}
              className="profile-image-icon-option"
            />
          </div>
        </div>
      </div>
    </>
  );
};
export default ProfileImageCarouselModal;

ProfileImageCarouselModal.propTypes = {
  handleCloseModal: PropTypes.func.isRequired,
  targetUserId: PropTypes.string.isRequired,
};
