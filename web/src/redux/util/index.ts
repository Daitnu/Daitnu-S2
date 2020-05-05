import { call, put } from 'redux-saga/effects';
import UserApi from '~/library/request/UserApi';
import { HTTPResponse } from '~/@types/response/success';
import { BusinessErrorResponse } from '~/@types/response/error';

export const makeApiCallSagaFunc = (type: string) =>
  function* (action) {
    const [SUCCESS, FAILURE] = [`${type}_SUCCESS`, `${type}_FAILURE`];
    try {
      const api = new UserApi();
      const payload = yield call(api.register.bind(api), action.payload);
      yield put({ type: SUCCESS, payload });
    } catch (err) {
      yield put({ type: FAILURE, payload: err });
    }
  };

export interface ApiState<T> {
  loading: boolean;
  data: null | Promise<HTTPResponse<T>>;
  error: null | Promise<BusinessErrorResponse>;
}

const initialState = { loading: false, data: null, error: null };

export const makeReducer = <T, R>(type: string) => {
  const REQUEST = `${type}_REQUEST` as 'REQUEST';
  const SUCCESS = `${type}_SUCCESS` as 'SUCCESS';
  const FAILURE = `${type}_FAILURE` as 'FAILURE';
  type Request = {
    type: typeof REQUEST;
    payload: { data: R };
  };
  type Success = {
    type: typeof SUCCESS;
    payload: Promise<HTTPResponse<T>>;
  };
  type Failure = {
    type: typeof FAILURE;
    payload: Promise<BusinessErrorResponse>;
  };
  type Action = Request | Success | Failure;

  return (state: ApiState<T> = initialState, action: Action): ApiState<T> => {
    switch (action.type) {
      case REQUEST:
        return {
          ...initialState,
          loading: true,
        };
      case SUCCESS:
        return {
          ...state,
          loading: false,
          data: action.payload,
          error: null,
        };
      case FAILURE:
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
};
