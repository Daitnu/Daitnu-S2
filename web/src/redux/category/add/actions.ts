import { AddCategoryParam } from '~/@types/request/category';
import { HTTPResponse } from '~/@types/response/success';
import { ResponseCategory } from '~/@types/response/category';
import { BusinessErrorResponse } from '~/@types/response/error';

export const CATEGORY_ADD = 'CATEGORY_ADD' as const;
export const CATEGORY_ADD_REQUEST = 'CATEGORY_ADD_REQUEST' as const;
export const CATEGORY_ADD_SUCCESS = 'CATEGORY_ADD_SUCCESS' as const;
export const CATEGORY_ADD_FAILURE = 'CATEGORY_ADD_FAILURE' as const;

export const categoryAddRequest = (payload: AddCategoryParam) => ({
  type: CATEGORY_ADD_REQUEST,
  payload,
});

export const categoryAddSuccess = (payload: HTTPResponse<ResponseCategory>) => ({
  type: CATEGORY_ADD_SUCCESS,
  payload,
});

export const categoryAddFailure = (payload: BusinessErrorResponse) => ({
  type: CATEGORY_ADD_FAILURE,
  payload,
});
