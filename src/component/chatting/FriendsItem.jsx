import { Accordion } from 'react-bootstrap';
import { useMemo } from 'react';
import { StyledChattingItemNoPadding } from '../../styled-components/StyledForm';
import createRandomUser from '../utility/FakeUser';

const FriendsItem = () => {
  const fakeUsers = useMemo(() =>
    Array.from({ length: 10 }, () => createRandomUser())
  );
  return (
    <Accordion defaultActiveKey="0" className="custom-accordion underline">
      <Accordion.Item eventKey="0">
        <Accordion.Header>Friend</Accordion.Header>
        {fakeUsers.map((user, index) => (
          <StyledChattingItemNoPadding>
            <Accordion.Body
              style={{
                marginLeft: '0px',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
              }}
            >
              <img
                src={user.avatar}
                alt={`Friend ${index + 1}`}
                className="my-profile-intro"
              />
              <div className="custom-ml-30">
                <div className="profile-label">{user.username}</div>
                <div className="profile-description custom-mt-5">
                  {`${user.userId} ${user.email}`}
                </div>
              </div>
            </Accordion.Body>
          </StyledChattingItemNoPadding>
        ))}
      </Accordion.Item>
    </Accordion>
  );
};

export default FriendsItem;
