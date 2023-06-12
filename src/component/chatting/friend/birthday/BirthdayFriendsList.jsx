import {
  compareWithCurrentDate,
  returnCurrDateYYYYMMDD,
  returnMMDD,
} from 'component/utility/DateUtil';
import PropTypes from 'prop-types';
import { Trans } from 'react-i18next';
import { useSelector } from 'react-redux';

const BirthdayFriendsList = ({ handleCloseModal }) => {
  const { list: birthdayFriends } = useSelector((state) => state.user.birthday);

  const birthdayFriendsBeforeCurrDay = birthdayFriends.filter(
    (val) => compareWithCurrentDate(val.birthdate) === -1
  );

  const currBirthdayFriends = birthdayFriends.filter(
    (val) => compareWithCurrentDate(val.birthdate) === 0
  );

  const birthdayFriendsAfterCurrDay = birthdayFriends.filter(
    (val) => compareWithCurrentDate(val.birthdate) === 1
  );

  const groupByDate = (arr) =>
    arr.reduce((acc, val) => {
      const date = returnCurrDateYYYYMMDD(val.birthdate);
      const accumulator = { ...acc }; // shallow clone of acc
      (accumulator[date] = accumulator[date] || []).push(val);
      return accumulator;
    }, {});

  const sortedGroups = (groups) => {
    const sortedKeys = Object.keys(groups).sort(
      (a, b) => new Date(a) - new Date(b)
    );
    return sortedKeys.reduce(
      (acc, key) => ({ ...acc, [key]: groups[key] }),
      {}
    );
  };

  const birthdayGroupsBefore = sortedGroups(
    groupByDate(birthdayFriendsBeforeCurrDay)
  );

  const birthdayGroupsToday = sortedGroups(groupByDate(currBirthdayFriends));
  const birthdayGroupsAfter = sortedGroups(
    groupByDate(birthdayFriendsAfterCurrDay)
  );

  const renderGroups = (groups) =>
    Object.entries(groups).map(([date, friends]) => (
      <div key={date}>
        <div style={{ fontSize: '12px', marginTop: '10px' }}>
          {returnMMDD(date)}
        </div>
        {friends.map((friend, index) => (
          <div
            key={friend.id}
            className="birthday-friend-box"
            style={{
              display: 'flex',
              padding: '5px',
              paddingLeft: '10px',
              alignItems: 'center',
            }}
          >
            <img
              src={friend.avatar}
              alt={`Friend ${index + 1}`}
              className="profile-intro"
              aria-hidden="true"
            />
            <div
              className="custom-ml-30"
              style={{
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center',
                width: '100%',
              }}
            >
              <div className="profile-label">{friend.name}</div>
              <div>
                <button
                  type="button"
                  className="give-gift"
                  onClick={() => alert('나중에 작업할거에요 ㅎ')}
                >
                  <Trans i18nKey="friend.birthday.giveGift" />
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    ));

  const groupBeforeResult = renderGroups(birthdayGroupsBefore);
  const groupTodayResult = renderGroups(birthdayGroupsToday);
  const groupAfterResult = renderGroups(birthdayGroupsAfter);

  return (
    <div className="profile-screen">
      <div className="modal-header">
        <button
          type="button"
          className="btn-close"
          onClick={handleCloseModal}
          aria-label="hidden"
        />
      </div>
      <div className="multi-profile-title">
        <Trans i18nKey="friend.birthday.friends" />
      </div>
      <div style={{ marginLeft: '10px', padding: '10px' }}>
        {groupBeforeResult.length !== 0 && (
          <>
            <div style={{ fontWeight: 'bold' }}>
              <Trans i18nKey="friend.birthday.before" />
            </div>
            {groupBeforeResult}
            <div className="underline mb-10" />
          </>
        )}
        {groupTodayResult.length !== 0 && (
          <>
            <div style={{ fontWeight: 'bold' }}>
              <Trans i18nKey="friend.birthday.current" />
            </div>
            {groupTodayResult}
            <div className="underline mb-10" />
          </>
        )}

        {groupAfterResult.length !== 0 && (
          <>
            <div style={{ fontWeight: 'bold' }}>
              <Trans i18nKey="friend.birthday.after" />
            </div>
            {groupAfterResult}
          </>
        )}
      </div>
    </div>
  );
};

export default BirthdayFriendsList;

/**
 * the reason why we should use TypeScript!!
 */
BirthdayFriendsList.propTypes = {
  handleCloseModal: PropTypes.func.isRequired,
};
