import { Modal } from 'react-bootstrap';
import PropTypes from 'prop-types';

const CarouselModal = ({ show, onHide, children }) => (
  <Modal
    show={show}
    onHide={onHide}
    centered
    size="lg"
    contentClassName="carousel-modal-content"
  >
    <Modal.Body>{children}</Modal.Body>
  </Modal>
);

CarouselModal.propTypes = {
  show: PropTypes.bool.isRequired,
  onHide: PropTypes.func.isRequired,
  children: PropTypes.node.isRequired,
};

export default CarouselModal;
