import PropTypes from 'prop-types';

const AddFriendModal = ({ handleCloseModal, userId }) => {
  console.log('test :: ', userId);

  return (
    <>
      <div className="modal-header">
        <button
          type="button"
          className="btn-close"
          onClick={handleCloseModal}
          aria-label="hidden"
        />
      </div>
      <div
        className="modal-body"
        style={{ margin: 'auto', textAlign: 'center', width: '100%' }}
      >
        <div style={{ display: 'flex', marginLeft: '30px' }}>testset</div>
        <div style={{ height: '67%', width: '100%', backgroundColor: 'red' }}>
          sdfsdfsdf
        </div>
        <div>test222</div>
      </div>
      <div className="modal-footer" style={{ height: '18%' }}>
        footer
      </div>
    </>
  );
};
export default AddFriendModal;

/**
 * the reason why we should use TypeScript!!
 */
AddFriendModal.propTypes = {
  handleCloseModal: PropTypes.func.isRequired,
  // secondModalStatus: PropTypes.bool,
  userId: PropTypes.string.isRequired,
};
