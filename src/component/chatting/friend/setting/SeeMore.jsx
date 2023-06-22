import {
  faBullhorn,
  faCalendarWeek,
  faCircleInfo,
  faEnvelope,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Trans } from 'react-i18next';
import { StyledChattingIntroLabel } from 'styled-components/StyledForm';

const SeeMore = () => {
  console.log('testing용');

  // Recalculate animation speed when data changes
  return (
    <div>
      <StyledChattingIntroLabel>
        <div className="main-profile-title">
          <Trans i18nKey="setting.seeMore" />
        </div>
      </StyledChattingIntroLabel>
      <div
        className="mt-3 underline"
        style={{
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          backgroundColor: 'gray',
          border: '1px solid black',
          borderTopLeftRadius: '5px',
          borderTopRightRadius: '5px',
          width: '98%',
          margin: 'auto',
          height: '74px',
        }}
      >
        <div>홍길동</div>
        <div>thelovemsg@naver.com</div>
      </div>
      <div
        style={{
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          backgroundColor: 'gray',
          border: '1px solid black',
          borderTop: 'none',
          borderBottomLeftRadius: '5px',
          borderBottomRightRadius: '5px',
          width: '98%',
          margin: 'auto',
          height: '43px',
        }}
      >
        test
      </div>
      <div
        className="mt-5"
        style={{
          width: '90%',
          margin: 'auto',
        }}
      >
        <div
          style={{
            display: 'flex',
            justifyContent: 'space-between', // Optional: Adds space between the columns
          }}
        >
          <FontAwesomeIcon className="setting-icon" icon={faEnvelope} />
          <FontAwesomeIcon className="setting-icon" icon={faCalendarWeek} />
          <FontAwesomeIcon className="setting-icon" icon={faCircleInfo} />
          <FontAwesomeIcon className="setting-icon" icon={faBullhorn} />
        </div>
      </div>
      <div className="underline mt-4" />
    </div>
  );
};

export default SeeMore;
