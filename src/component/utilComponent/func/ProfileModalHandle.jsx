import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
  REMOVE_USER_MULTI_PROFILE_INFO_REQUEST,
  SET_USER_MULTI_PROFILE_STATUS_FALSE,
} from 'reducers/user/userMultiProfile';

const profileModalHandle = () => {
  const { success } = useSelector((state) => state.user.multiProfile);
  const dispatch = useDispatch();

  useEffect(() => {
    if (success) {
      dispatch(SET_USER_MULTI_PROFILE_STATUS_FALSE());
    }
  }, [success]);

  const handleImageClick = (handleSecondMoldal) => {
    console.log('secondMoldal :: ', handleSecondMoldal);
  };

  const handleBookmarkClick = () => {
    console.log('handleBookmarkClick !!');
  };

  const handleSettingClick = () => {
    console.log('handleSettingClick !!');
  };

  const handleProfileDelete = (userInfo) => {
    dispatch(REMOVE_USER_MULTI_PROFILE_INFO_REQUEST(userInfo));
  };

  const handleProfileRotate = () => {
    console.log('showProfileRotate !!');
  };

  const handleOneToOneChat = () => {
    console.log('handleOneToOneChat !!');
  };

  const handleVioceCall = () => {
    console.log('handleVideoCall !!');
  };

  const handleVideoCall = () => {
    console.log('handleVideoCall !!');
  };

  return {
    handleImageClick,
    handleBookmarkClick,
    handleSettingClick,
    handleProfileDelete,
    handleProfileRotate,
    handleOneToOneChat,
    handleVioceCall,
    handleVideoCall,
  };
};

export default profileModalHandle;
