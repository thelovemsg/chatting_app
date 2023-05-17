// ESM
import { faker } from '@faker-js/faker';

export function createRandomUser() {
  return {
    userId: faker.datatype.uuid(),
    username: faker.internet.userName(),
    email: faker.internet.email(),
    avatar: faker.image.avatar(),
    password: faker.internet.password(),
    birthdate: faker.date.birthdate(),
    registeredAt: faker.date.past(),
  };
}

export function createRandomChatMessage(senderId, receiverId) {
  return {
    userId: faker.datatype.uuid(),
    username: faker.internet.userName(),
    senderId,
    receiverId,
    email: faker.internet.email(),
    avatar: faker.image.avatar(),
    content: faker.lorem.sentence(),
    registeredAt: faker.date.past(),
  };
}
