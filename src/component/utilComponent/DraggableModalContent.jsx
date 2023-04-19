import PropTypes from 'prop-types';

const DraggableModalContent = ({
  handleCloseModal,
  userInfo,
  stateContent,
  footerContent,
}) => (
  <>
    <div className="modal-header">
      <h5 className="modal-title">Avatar</h5>
      <button
        type="button"
        className="btn-close"
        onClick={handleCloseModal}
        aria-label="hidden"
      />
    </div>
    <div className="modal-body" style={{ margin: 'auto', textAlign: 'center' }}>
      <div style={{ height: '60%' }}>test1</div>
      {userInfo.avatar && <img src={userInfo.avatar} alt="Selected Avatar" />}
      <div>{stateContent}</div>
    </div>
    <div className="modal-footer">{footerContent}</div>
  </>
);

export default DraggableModalContent;

DraggableModalContent.propTypes = {
  handleCloseModal: PropTypes.func.isRequired,
  userInfo: PropTypes.objectOf(PropTypes.object).isRequired,
  stateContent: PropTypes.string.isRequired,
  footerContent: PropTypes.string.isRequired,
};
