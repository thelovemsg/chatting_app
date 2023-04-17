import { faPlus } from '@fortawesome/free-solid-svg-icons';
import Accordion from 'react-bootstrap/Accordion';
import { Trans } from 'react-i18next';
import { Button, Modal } from 'react-bootstrap';
import { useState, useMemo } from 'react';
import Draggable from 'react-draggable';
import createRandomUser from '../utility/FakeUser';
import {
  StyledAccordionBodyMultiProfile,
  StyledFontAwesomeIconPlus,
} from '../../styled-components/StyledForm';

const MutiProfile = () => {
  /*
    multi profile은 3개 이상 존재하면 + 버튼은 사라지게 해야함.
  */

  /**
   * TODO
   * use useMemo hook in order to get avatars of user just one time.
   */

  const DraggableDialog = useMemo(
    ({ className, style, children, ...props }) => (
      <div
        className={className}
        style={{ ...style, pointerEvents: 'none' }}
        {...props}
      >
        {children}
      </div>
    )
  );

  const fakeUsers = useMemo(
    () => Array.from({ length: 2 }, () => createRandomUser()),
    []
  );

  const [showModal, setShowModal] = useState(false);
  const [selectedAvatar, setSelectedAvatar] = useState(null);

  const handleAvatarClick = (avatar) => {
    setSelectedAvatar(avatar);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <>
      <Accordion defaultActiveKey="0" className="custom-accordion underline ">
        <Accordion.Item eventKey="0">
          <Accordion.Header>
            <Trans i18nKey="friend.multi_profile" />
          </Accordion.Header>
          <StyledAccordionBodyMultiProfile>
            {fakeUsers.map((user, index) => (
              <button
                className="avatar-button"
                onClick={() => handleAvatarClick(user.avatar)}
                aria-label={`Friend ${index + 1}`}
                style={{
                  border: 'none',
                  background: 'transparent',
                  padding: 0,
                }}
                type="button"
              >
                <img
                  src={user.avatar}
                  alt={`Friend ${index + 1}`}
                  className="profile-others"
                />
              </button>
            ))}
            {/* 여기 item들이 hover이벤트가 먹어야함. */}
            <StyledFontAwesomeIconPlus icon={faPlus} />
            <div>test</div>
          </StyledAccordionBodyMultiProfile>
        </Accordion.Item>
      </Accordion>
      <Draggable>
        <Modal
          show={showModal}
          onHide={handleCloseModal}
          dialogAs={DraggableDialog}
        >
          <Modal.Header closeButton>
            <Modal.Title>Avatar</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {selectedAvatar && (
              <img src={selectedAvatar} alt="Selected Avatar" />
            )}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseModal}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>
      </Draggable>
    </>
  );
};

export default MutiProfile;
