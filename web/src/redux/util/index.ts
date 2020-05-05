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
