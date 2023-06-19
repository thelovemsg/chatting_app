import {
  faComment,
  faComments,
  faMagnifyingGlass,
  faXmark,
} from '@fortawesome/free-solid-svg-icons';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Trans } from 'react-i18next';
import { StyledChattingIntroLabel } from 'styled-components/StyledForm';
import { useRef, useEffect } from 'react';

const ChattingList = ({
  showSearchBox,
  handleSearchBox,
  handleSearchBoxClose,
  searchInput,
  handleSearchInputChange,
}) => {
  const searchInputRef = useRef(null);

  useEffect(() => {
    if (showSearchBox && searchInputRef.current) {
      searchInputRef.current.focus();
    }
  }, [showSearchBox]);

  return (
    <div>
      <StyledChattingIntroLabel>
        <div className="main-profile-title">
          <Trans i18nKey="chatting.title" />
        </div>
        <div className="custom-mr-10 flex-align-items-center">
          <FontAwesomeIcon
            className="cursor-pointer"
            icon={faMagnifyingGlass}
            onClick={handleSearchBox}
          />
          <FontAwesomeIcon
            className="cursor-pointer"
            style={{ width: '29px', marginLeft: '10px', marginRight: '10px' }}
            icon={faComments}
          />
          <FontAwesomeIcon
            className="cursor-pointer"
            style={{ width: '23px' }}
            icon={faComment}
          />
        </div>
      </StyledChattingIntroLabel>
      <div>
        {showSearchBox && (
          <div style={{ padding: '10px', width: '100%' }}>
            <FontAwesomeIcon
              icon={faMagnifyingGlass}
              className="profile-search-icon"
            />
            <input
              ref={searchInputRef}
              className="profile-search"
              type="text"
              value={searchInput}
              onChange={handleSearchInputChange}
            />
            <FontAwesomeIcon
              icon={faXmark}
              className="custom-ml-10 w-20 h-20 cursor-pointer"
              onClick={handleSearchBoxClose}
            />
          </div>
        )}
      </div>
    </div>
  );
};

export default ChattingList;

ChattingList.propTypes = {
  showSearchBox: PropTypes.bool.isRequired,
  handleSearchBox: PropTypes.func.isRequired,
  handleSearchBoxClose: PropTypes.func.isRequired,
  searchInput: PropTypes.string,
  handleSearchInputChange: PropTypes.func.isRequired,
};

ChattingList.defaultProps = {
  searchInput: null,
};
