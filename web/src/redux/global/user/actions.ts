import { User } from '~/@types/state/user';

export const USER = 'USER' as const;
export const SAVE_USER = 'SAVE_USER' as const;
export const CLEAR_USER = 'CLEAR_USER' as const;

export const saveUser = (payload: User) => ({
  type: SAVE_USER,
  payload,
});

export const clearUser = (payload: User) => ({
  type: CLEAR_USER,
  payload,
});
