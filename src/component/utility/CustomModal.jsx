import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import { Spinner } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';

const CustomModal = ({ show, title, message, onClose, onAction }) => {
  const { logoutDone } = useSelector((state) => state.user);
  const [isLoading, setIsLoading] = useState(false);

  const handleActionClick = async () => {
    setIsLoading(true);
    if (onAction) {
      await onAction();
      setIsLoading(false);
    }
  };

  const handleActionClose = () => {
    setIsLoading(false);
    onClose();
  };

  useEffect(() => {
    setIsLoading(false);
  }, [show]);

  useEffect(() => {
    if (logoutDone) onClose();
  }, [logoutDone]);

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
          ''
        )}
        <Button
          variant="primary"
          onClick={handleActionClick}
          style={{ width: '80px' }}
        >
          Yes
        </Button>
        <Button
          variant="secondary"
          onClick={handleActionClose}
          style={{ width: '80px' }}
        >
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default CustomModal;
