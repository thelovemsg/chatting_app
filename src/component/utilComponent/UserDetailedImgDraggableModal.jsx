import Draggable from 'react-draggable';
import PropTypes from 'prop-types';

const UserDetailedImgDraggableModal = ({ show, children, style }) => (
  <Draggable>
    <div
      className={`modal fade show ${show ? 'd-block' : 'd-none'}`}
      style={{ ...style, zIndex: 1050 }}
      tabIndex="-1"
      role="dialog"
      aria-labelledby="draggableModalLabel"
      aria-hidden="true"
    >
      <div
        className="modal-dialog modal-dialog-centered"
        role="document"
        style={{ pointerEvents: 'none' }}
      >
        <div
          className="modal-content"
          style={{ pointerEvents: 'auto', zIndex: 1050 }}
        >
          {children}
        </div>
      </div>
    </div>
  </Draggable>
);

UserDetailedImgDraggableModal.propTypes = {
  show: PropTypes.bool.isRequired,
  children: PropTypes.oneOfType([
    PropTypes.arrayOf(PropTypes.node),
    PropTypes.node,
  ]).isRequired,
  style: PropTypes.object,
};

export default UserDetailedImgDraggableModal;
