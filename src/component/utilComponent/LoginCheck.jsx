import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { useEffect } from 'react';
import { LOG_IN_CHECK_REQUEST } from 'reducers/user';
import { t } from 'i18next';

const LoginCheck = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const loginCheckError = useSelector((state) => state.user.loginCheckFailure);

  useEffect(() => {
    dispatch(LOG_IN_CHECK_REQUEST({}));
  }, [dispatch]);

  useEffect(() => {
    if (loginCheckError?.message) {
      alert(t('error.login_check_error'));
      navigate('/signin');
    }
  }, [loginCheckError]);

  // You could render children here or return null if you don't want anything displayed
  return null;
};

export default LoginCheck;
