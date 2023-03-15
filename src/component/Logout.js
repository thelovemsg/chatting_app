// In your other component file

import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import CustomModal from "../component/utility/CustomModal";
import { LOG_OUT_REQUEST } from "../reducers/user";
import { Trans } from "react-i18next";

const Logout = () => {
  const { loginError } = useSelector((state) => state.user);

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
        Logout
      </div>
      <CustomModal
        show={showModal}
        title={<Trans i18nKey="carousel.carousel_image_1_title" />}
        message="My Modal Message"
        onClose={handleCloseModal}
        actionType={LOG_OUT_REQUEST()}
      />
    </>
  );
};

export default Logout;
