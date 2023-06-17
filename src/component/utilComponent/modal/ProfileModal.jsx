import { PropTypes } from 'prop-types';
import Draggable from 'react-draggable';

const ProfileModal = ({ show, children, style, disabled }) =>
  show && (
    <div className="modal-backdrop show draggable-model-background">
      <div className="modal show d-block" tabIndex="-1">
        <Draggable disabled={disabled}>
          <div className="modal-dialog">
            <div
              className="modal-content draggable-modal-content"
              style={style}
            >
              {children}
            </div>
          </div>
        </Draggable>
      </div>
    </div>
  );

ProfileModal.propTypes = {
  show: PropTypes.bool.isRequired,
  children: PropTypes.node.isRequired,
  style: PropTypes.objectOf(PropTypes.string),
  disabled: PropTypes.bool,
};

ProfileModal.defaultProps = {
  style: {},
  disabled: false,
};

export default ProfileModal;
