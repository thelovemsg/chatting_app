import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguaeDetector from 'i18next-browser-languagedetector';

i18n
  .use(LanguaeDetector) // 사용자 언어 탐지
  .use(initReactI18next) // i18n 객체를 react-18next에 전달
  .init({
    // for all options read: https://www.i18next.com/overview/configuration-options
    debug: true,
    fallbackLng: 'ko',
    interpoltion: {
      escapeValue: false,
    },
    resources: {
      en: {
        translation: {
          // 번역본 쓸 공간
          navbar: {
            home: 'home',
            login: 'login',
            chatting: 'chatting',
            profile: 'profile',
            register: 'register',
          },
          description: {
            part1: 'Edit src/App.js and save to reload.',
            part2: 'Learn React',
          },
          carousel: {
            carousel_image_1_title: 'First',
            carousel_image_1_description:
              "Hello! Welcome To MSG's Mini Project!",
            carousel_image_2_title: 'Second',
            carousel_image_2_description:
              "I'd like to make a variety of things here!",
            carousel_image_3_title: 'Third',
            carousel_image_3_description:
              "If you don't like it, just give me any feedback!",
          },
          register: {
            checkEmail: 'Check email',
            wrongPhoneNumberFormat: 'Wrong phone number format',
            nicknameEmpty: 'Nickname cannot be empty',
            alreadyExists: 'already exists',
            passwordMismatch: "Password doesn't match",
            passwordRegexCheck:
              '최소 8 자, 하나 이상의 대문자, 하나의 소문자, 하나의 숫자 및 하나의 특수 문자 필요',
            errorFound: 'please check input datas',
          },
        },
      },
      ko: {
        translation: {
          // 번역본 쓸 공간
          navbar: {
            home: '홈으로',
            login: '로그인',
            chatting: '채팅하기',
            profile: '프로필',
            register: '회원가입',
          },
          description: {
            part1: 'src/App.js를 편집하고 저장하여 다시 로드합니다.',
            part2: 'React 배우러가기',
          },
          carousel: {
            carousel_image_1_title: '첫번째',
            carousel_image_1_description:
              '안녕하세요! MSG의 미니 프로젝트에 오신 것을 환영합니다!',
            carousel_image_2_title: '두번째',
            carousel_image_2_description: '이곳에서 많은 것을 해보고 싶어요!',
            carousel_image_3_title: '세번째',
            carousel_image_3_description:
              '마음에 안드신다면, 어떤 피드백이든 주세요!',
          },
          register: {
            checkEmail: '이메일을 확인하세요',
            wrongPhoneNumberFormat: '잘못된 전화번호 형식',
            nicknameEmpty: '별명을 입력해주세요',
            alreadyExists: '이 이미 존재합니다',
            passwordMismatch: '비밀번호가 일치하지 않습니다.',
            passwordRegexCheck:
              '최소 8 자, 하나 이상의 대문자, 하나의 소문자, 하나의 숫자 및 하나의 특수 문자 필요',
            errorFound: '입력 값을 확인해 주세요',
          },
        },
      },
    },
  });

export default i18n;
