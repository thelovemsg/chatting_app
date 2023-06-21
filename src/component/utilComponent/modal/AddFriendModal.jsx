import { t } from 'i18next';
import PropTypes from 'prop-types';
import { useState } from 'react';
import { Tab, Tabs } from 'react-bootstrap';
import { Trans } from 'react-i18next';

const AddFriendModal = ({ handleCloseModal, userId }) => {
  console.log('test :: ', userId);

  const [nameInput, setNameInput] = useState('');
  const [phoneNumberInput, setPhoneNumberInput] = useState('');
  const [idInput, setIdInput] = useState('');

  const handleNameInput = (event) => {
    setNameInput(event.target.value);
  };

  const handlePhoneNumberInput = (event) => {
    setPhoneNumberInput(event.target.value);
  };

  const handleIdInput = (event) => {
    setIdInput(event.target.value);
  };

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
        <div style={{ display: 'flex', marginLeft: '20px' }}>
          <Trans i18nKey="friend.add.title" />
        </div>
        <div style={{ height: '45%', width: ' 100%' }}>
          <Tabs
            defaultActiveKey="addWithTelnum"
            id="uncontrolled-tab-example"
            className="mt-3"
          >
            <Tab eventKey="addWithTelnum" title={t('friend.add.addWithTelnum')}>
              <div
                className="mt-4 underline"
                style={{
                  margin: 'auto',
                  width: '90%',
                  fontSize: '12px',
                  display: 'flex',
                  backgroundColor: 'none',
                }}
              >
                <input
                  className="profile-modal-input-content"
                  type="text"
                  onChange={handleNameInput}
                />
                <div className="profile-modal-input-counter">{`${nameInput.length}/20`}</div>
              </div>
              <div
                className="mt-4 underline"
                style={{
                  margin: 'auto',
                  width: '90%',
                  fontSize: '12px',
                  display: 'flex',
                }}
              >
                <input
                  type="text"
                  className="profile-modal-input-content"
                  onChange={handlePhoneNumberInput}
                />
                <div className="profile-modal-input-counter">{`${phoneNumberInput.length}/20`}</div>
              </div>
            </Tab>
            <Tab eventKey="addWithId" title={t('friend.add.addWithId')}>
              <div
                className="mt-4 underline"
                style={{
                  margin: 'auto',
                  width: '90%',
                  fontSize: '12px',
                  display: 'flex',
                }}
              >
                <input
                  type="text"
                  className="profile-modal-input-content"
                  onChange={handleIdInput}
                />
                <div className="profile-modal-input-counter">{`${idInput.length}/20`}</div>
              </div>
              <div className="mt-4" style={{ margin: 'auto', width: '90%' }}>
                여기 뭐가 들어가야함
              </div>
            </Tab>
          </Tabs>
        </div>
        <div>
          <Trans i18nKey="friend.add.telNumDesc" />
        </div>
      </div>
      <div className="modal-footer" style={{ height: '18%' }}>
        <button
          type="button"
          className="multi-profile-btn"
          onClick={handleCloseModal}
        >
          click!
        </button>
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
