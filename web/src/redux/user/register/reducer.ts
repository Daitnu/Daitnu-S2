import { REGISTER_REQUEST, REGISTER_SUCCESS, REGISTER_FAILURE } from './actions';
import { UserRegisterApiState, UserActionTypes } from './types';

const initialState: UserRegisterApiState = { loading: false, data: null, error: null };

const userRegisterReducer = (
  state = initialState,
  action: UserActionTypes,
): UserRegisterApiState => {
  switch (action.type) {
    case REGISTER_REQUEST:
      return {
        ...initialState,
        loading: true,
      };
    case REGISTER_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.payload,
        error: null,
      };
    case REGISTER_FAILURE:
      return {
        ...state,
        loading: false,
        data: null,
        error: action.payload,
      };
    default:
      return state;
  }
};

export default userRegisterReducer;
