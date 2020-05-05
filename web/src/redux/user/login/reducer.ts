import { UserLoginApiState, UserLoginActionTypes } from './types';
import { LOGIN_SUCCESS, LOGIN_REQUEST, LOGIN_FAILURE } from './actions';

const initialState: UserLoginApiState = { loading: false, data: null, error: null };

const userLoginReducer = (
  state: UserLoginApiState = initialState,
  action: UserLoginActionTypes,
) => {
  switch (action.type) {
    case LOGIN_REQUEST:
      return {
        ...initialState,
        loading: true,
      };
    case LOGIN_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.payload,
        error: null,
      };
    case LOGIN_FAILURE:
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

export default userLoginReducer;
