// In your modal component file

import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import { Spinner } from "react-bootstrap";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";

const CustomModal = ({ show, title, message, onClose, actionType }) => {
  const [isLoading, setIsLoading] = useState(false);
  const dispatch = useDispatch();

  const dispatchAction = () => {
    setIsLoading(true);
    return new Promise((resolve, reject) => {
      dispatch(actionType);
      resolve();
    });
  };

  const handleActionClick = async () => {
    setIsLoading(true);
    await dispatchAction();
  };

  const handleActionClose = () => {
    setIsLoading(false);
    onClose();
  };

  useEffect(() => {
    setIsLoading(false);
  }, [show]);

  return (
    <Modal show={show} onHide={onClose}>
      <Modal.Header closeButton className="border-0">
        <Modal.Title>{title}</Modal.Title>
      </Modal.Header>
      <Modal.Body className="border-0 ml-3">{message}</Modal.Body>
      <Modal.Footer>
        {isLoading ? (
          <Spinner animation="border" variant="info" className="mt-2" />
        ) : (
          ""
        )}
        <Button
          variant="primary"
          onClick={handleActionClick}
          style={{ width: "80px" }}
        >
          Yes
        </Button>
        <Button
          variant="secondary"
          onClick={handleActionClose}
          style={{ width: "80px" }}
        >
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default CustomModal;
