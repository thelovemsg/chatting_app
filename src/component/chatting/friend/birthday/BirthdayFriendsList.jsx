import {
  compareWithCurrentDate,
  returnDateToMMDD,
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
      const accumulator = { ...acc }; // shallow clone of acc
      (accumulator[val.birthdate] = accumulator[val.birthdate] || []).push(val);
      return accumulator;
    }, {});

  const birthdayGroupsBefore = groupByDate(birthdayFriendsBeforeCurrDay);
  const birthdayGroupsToday = groupByDate(currBirthdayFriends);
  const birthdayGroupsAfter = groupByDate(birthdayFriendsAfterCurrDay);

  const renderGroups = (groups) =>
    Object.entries(groups).map(([date, friends]) => (
      <div key={date}>
        <h2>{returnDateToMMDD(date)}</h2>
        {friends.map((friend) => (
          <div key={friend.id}>{friend.name}</div>
        ))}
      </div>
    ));

  return (
    <div className="multi-profile-screen">
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
      <div>
        <div>지난 생일</div>
        {renderGroups(birthdayGroupsBefore)}
        <div>오늘 생일</div>
        {renderGroups(birthdayGroupsToday)}
        <div>다가오는 생일</div>
        {renderGroups(birthdayGroupsAfter)}
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
