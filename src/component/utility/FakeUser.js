// ESM
import { faker } from '@faker-js/faker';

export function createRandomUser(inputDate) {
  return {
    id: faker.datatype.uuid(),
    name: faker.internet.userName(),
    email: faker.internet.email(),
    avatar: faker.image.avatar(),
    password: faker.internet.password(),
    birthdate: inputDate ? new Date(inputDate) : faker.date.birthdate(),
    description: faker.lorem.sentence(),
    registeredAt: faker.date.past(),
  };
}

export function createRandomChatMessage(senderId, receiverId) {
  return {
    userId: faker.datatype.uuid(),
    name: faker.internet.userName(),
    senderId,
    receiverId,
    timestamp: new Date(),
    email: faker.internet.email(),
    avatar: faker.image.avatar(),
    content: faker.lorem.sentence(),
    registeredAt: faker.date.past(),
  };
}

export function createRandomUuid() {
  return faker.datatype.uuid();
}

/**
 * 이거 ... 테스트로 avatar에도 순서가 있어야 하는데...
 * 객체 안에 배열로 물어서 들어가는게 맞는거 같음
 * @returns
 */
export function createRandomProfile() {
  return {
    id: faker.datatype.uuid(),
    name: faker.internet.userName(),
    uploadDate: new Date(),
    mainAvatar: faker.image.avatar(),
    description: faker.lorem.sentence(),
    avatars: [],
  };
}
