import { categoryGetRequest, categoryGetSuccess, categoryGetFailure } from './actions';

export type CategoryGetActionTypes =
  | ReturnType<typeof categoryGetRequest>
  | ReturnType<typeof categoryGetSuccess>
  | ReturnType<typeof categoryGetFailure>;
