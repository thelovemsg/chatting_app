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
  StyledChattingScreenIconsTop,
  StyledChattingScreenLeft,
  StyledChattingScreenRight,
  StyledTopicComponent,
} from "../../styled-components/StyledForm";
import MyProfile from "./MyProfile";

const Friends = () => {
  return (
    <StyledChattingScreen>
      <StyledChattingScreenLeft>
        <StyledChattingScreenIconsTop>
          <StyleFontAwesomeIcon icon={faUser} />
          <StyleFontAwesomeIcon icon={faCommentDots} />
          <StyleFontAwesomeIcon icon={faBell} />
        </StyledChattingScreenIconsTop>
      </StyledChattingScreenLeft>
      <StyledChattingScreenRight>
        <div className="custom_align">
          <MyProfile></MyProfile>
          <StyledTopicComponent></StyledTopicComponent>
          <StyledTopicComponent></StyledTopicComponent>
        </div>
      </StyledChattingScreenRight>
    </StyledChattingScreen>
  );
};

export default Friends;
