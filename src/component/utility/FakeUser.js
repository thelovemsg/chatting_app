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
