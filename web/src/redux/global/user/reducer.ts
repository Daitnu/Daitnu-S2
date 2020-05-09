import { UserActionTypes } from './types';
import { User } from '~/@types/state/user';
import { SAVE_USER, CLEAR_USER } from './actions';

const initialState: User = { id: '', name: '', email: '' };

const userReducer = (state: User = initialState, action: UserActionTypes): User => {
  const { payload, type } = action;
  switch (type) {
    case SAVE_USER:
      return {
        id: payload.id,
        name: payload.name,
        email: payload.email,
      };
    case CLEAR_USER:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

export default userReducer;
