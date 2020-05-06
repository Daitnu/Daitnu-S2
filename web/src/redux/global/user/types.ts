import { saveUser, clearUser } from './actions';

export type UserActionTypes = ReturnType<typeof saveUser> | ReturnType<typeof clearUser>;
