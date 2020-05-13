import { HTTPResponse } from '~/@types/response/success';
import { ResponseCategory } from '~/@types/response/category';
import { BusinessErrorResponse } from '~/@types/response/error';

export const CATEGORY_GET = 'CATEGORY_GET' as const;
export const CATEGORY_GET_REQUEST = 'CATEGORY_GET_REQUEST' as const;
export const CATEGORY_GET_SUCCESS = 'CATEGORY_GET_SUCCESS' as const;
export const CATEGORY_GET_FAILURE = 'CATEGORY_GET_FAILURE' as const;

export const categoryGetRequest = () => ({ type: CATEGORY_GET_REQUEST });

export const categoryGetSuccess = (payload: HTTPResponse<ResponseCategory[]>) => ({
  type: CATEGORY_GET_SUCCESS,
  payload,
});

export const categoryGetFailure = (payload: BusinessErrorResponse) => ({
  type: CATEGORY_GET_FAILURE,
  payload,
});
