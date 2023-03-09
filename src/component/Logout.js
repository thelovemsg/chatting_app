// In your other component file

import { useState } from "react";
import { useDispatch } from "react-redux";
import CustomModal from "../component/utility/CustomModal";
import { LOG_OUT_REQUEST } from "../reducers/user";

const Logout = () => {
  const dispatch = useDispatch();
  const handleLogout = (e) => {
    if (e) e.preventDefault();
    dispatch(LOG_OUT_REQUEST());
  };

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
        title="로그아웃 하시겠습니까?"
        message="My Modal Message"
        onClose={handleCloseModal}
        action={handleLogout}
      />
    </>
  );
};

export default Logout;
