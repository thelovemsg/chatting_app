// In your modal component file

import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import { Spinner } from "react-bootstrap";
import { useState } from "react";

const CustomModal = ({ show, title, message, onClose, action }) => {
  const [isLoading, setIsLoading] = useState(false);

  const handleActionClick = () => {
    if (typeof action === "function") {
      console.log("tesitng...");
      setIsLoading(!isLoading);
      action();
    }
  };

  const handleActionClose = () => {
    setIsLoading(false);
    onClose();
  };

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
