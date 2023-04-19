import { PropTypes } from 'prop-types';
import Draggable from 'react-draggable';

const DraggableModal = ({ show, children }) =>
  show && (
    <div className="modal-backdrop show draggable-model-background">
      <div className="modal show d-block" tabIndex="-1">
        <Draggable>
          <div className="modal-dialog">
            <div
              className="modal-content draggable-modal-content"
              style={{ width: '300px', height: '600px', borderRadius: '5px' }}
            >
              {children}
            </div>
          </div>
        </Draggable>
      </div>
    </div>
  );

DraggableModal.propTypes = {
  show: PropTypes.bool.isRequired,
  children: PropTypes.node.isRequired,
};

export default DraggableModal;
