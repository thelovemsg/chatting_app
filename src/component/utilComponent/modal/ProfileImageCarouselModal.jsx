import { useEffect, useRef, useState } from 'react';
import PropTypes from 'prop-types';
import { useDispatch, useSelector } from 'react-redux';
import { GET_TARGET_USER_PROFILE_IMAGE_LIST_REQUEST } from 'reducers/user/userInfoSetting';

const ProfileImageCarouselModal = ({ handleCloseModal, targetUserId }) => {
  const dispatch = useDispatch();
  const { targetImageList } = useSelector((state) => state.user.info);
  useEffect(() => {
    dispatch(GET_TARGET_USER_PROFILE_IMAGE_LIST_REQUEST(targetUserId));
  }, []);

  console.log('targetUserId :: ', targetUserId);

  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const imageRef = useRef([]);

  function onSelectImage({ currentIndex, behavior = 'smooth' }) {
    setCurrentImageIndex(currentIndex);
    imageRef.current[currentIndex]?.scrollIntoView({
      inline: 'center',
      block: 'nearest',
      behavior,
    });
  }

  function handleImageMove(currentIndex) {
    const firstIndex = 0;
    const lastIndex = targetImageList.length - 1;

    if (currentIndex < firstIndex) {
      onSelectImage({ currentIndex: lastIndex, behavior: 'auto' });
      return;
    }
    if (currentIndex > lastIndex) {
      onSelectImage({ currentIndex: firstIndex, behavior: 'auto' });
      return;
    }

    onSelectImage({ currentIndex });
    // Return value at the end of function
  }

  return (
    <>
      <div className="modal-header">
        <button
          type="button"
          className="btn-close"
          onClick={handleCloseModal}
          aria-label="hidden"
        />
      </div>
      <div
        className="modal-body"
        style={{ margin: 'auto', textAlign: 'center', width: '100%' }}
      >
        <div className="profile-carousel-wrapper">
          <button
            type="button"
            onClick={() => handleImageMove(currentImageIndex - 1)}
          >
            previous
          </button>
          <div className="profile-image-carousel">
            {targetImageList?.length === 0 ? (
              <div>nothing</div>
            ) : (
              targetImageList.map((user, index) => (
                <div
                  ref={(el) => {
                    imageRef.current[index] = el;
                  }}
                >
                  <img
                    style={{ width: '300px' }}
                    alt={`user profile ${index + 1}`}
                    src={user.avatar}
                  />{' '}
                  {/* It should be src instead of scr */}
                </div>
              ))
            )}
          </div>
          <button
            type="button"
            onClick={() => handleImageMove(currentImageIndex + 1)}
          >
            next
          </button>
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
