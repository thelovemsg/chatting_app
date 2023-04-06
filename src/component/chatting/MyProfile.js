const MyProfile = () => {
  return (
    <div>
      <div className="intro-label">친구</div>
      <div className="display-flex-items profile-background-color">
        <img
          src="./img/kakaoFriends.png"
          alt="First slide"
          class="my-profile-intro"
        />
        <div className="custom-ml-20">
          <div className="profile-label">MSG(선준)</div>
          <div className="profile-description custom-mt-5">
            여기에 뭐가 들어가야해요
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyProfile;
