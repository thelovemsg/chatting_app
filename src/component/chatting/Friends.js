import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faUser,
  faComments,
  faCommentDots,
  faBell,
} from "@fortawesome/free-solid-svg-icons";

import {
  StyleFontAwesomeIcon,
  StyledChattingScreen,
  StyledChattingScreenIcons,
  StyledChattingScreenLeft,
  StyledChattingScreenRight,
  StyledTopicComponent,
} from "../../styled-components/StyledForm";

const Friends = () => {
  return (
    <StyledChattingScreen>
      <StyledChattingScreenLeft>
        <StyledChattingScreenIcons>
          <StyleFontAwesomeIcon icon={faUser} />
          <StyleFontAwesomeIcon icon={faCommentDots} />
          <StyleFontAwesomeIcon icon={faBell} />
        </StyledChattingScreenIcons>
      </StyledChattingScreenLeft>
      <StyledChattingScreenRight>
        <StyledTopicComponent />
        <StyledTopicComponent />
        <StyledTopicComponent />
      </StyledChattingScreenRight>
    </StyledChattingScreen>
  );
};

export default Friends;
