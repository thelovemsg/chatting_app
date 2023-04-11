// In your other component file

import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Trans } from 'react-i18next';
import CustomModal from './utility/CustomModal';
import { LOG_OUT_REQUEST } from '../reducers/user';

const Logout = () => {
  const dispatch = useDispatch();
  const { loginError } = useSelector((state) => state.user);

  const handleLogoutAction = async () => {
    dispatch(LOG_OUT_REQUEST());
  };

  useEffect(() => {
    if (loginError != null) {
      setShowModal(false);
    }
  }, [loginError]);

  const [showModal, setShowModal] = useState(false);

  const handleShowModal = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <>
      <div onClick={handleShowModal} className="color-yellow-first href-style">
        로그아웃
      </div>
      <CustomModal
        show={showModal}
        title={<Trans i18nKey="carousel.carousel_image_1_title" />}
        message="My Modal Message"
        onClose={handleCloseModal}
        onAction={handleLogoutAction}
      />
    </>
  );
};

export default Logout;
