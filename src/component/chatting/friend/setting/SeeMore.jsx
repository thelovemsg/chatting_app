import {
  faAngleRight,
  faBullhorn,
  faCalendarWeek,
  faCircleInfo,
  faEnvelope,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Trans } from 'react-i18next';
import {
  StyledChattingIntroLabel,
  StyledChattingMyInfo,
  StyledChattingMySubscribe,
} from 'styled-components/StyledForm';

const SeeMore = () => {
  console.log('testing용');

  return (
    <div>
      <StyledChattingIntroLabel>
        <div className="main-profile-title">
          <Trans i18nKey="setting.seeMore" />
        </div>
      </StyledChattingIntroLabel>
      <StyledChattingMyInfo className="mt-3 underline">
        <div className="custom-ml-20">홍길동</div>
        <div className="custom-ml-20">thelovemsg@naver.com</div>
      </StyledChattingMyInfo>
      <StyledChattingMySubscribe>
        <div className="custom-ml-20">My 구독</div>
        <button type="button" className="custom-mr-20">
          <FontAwesomeIcon icon={faAngleRight} />
        </button>
      </StyledChattingMySubscribe>
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
          <div
            style={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <FontAwesomeIcon className="setting-icon" icon={faEnvelope} />
            <div>
              <Trans i18nKey="setting.mail" />
            </div>
          </div>
          <div
            style={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <FontAwesomeIcon className="setting-icon" icon={faCalendarWeek} />
            <div>
              <Trans i18nKey="setting.calander" />
            </div>
          </div>
          <div
            style={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <FontAwesomeIcon className="setting-icon" icon={faCircleInfo} />
            <div>
              <Trans i18nKey="setting.info" />
            </div>
          </div>
          <div
            style={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <FontAwesomeIcon className="setting-icon" icon={faBullhorn} />
            <div>
              <Trans i18nKey="setting.notice" />
            </div>
          </div>
        </div>
      </div>
      <div className="underline mt-2" />
    </div>
  );
};

export default SeeMore;
