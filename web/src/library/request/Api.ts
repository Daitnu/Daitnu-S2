import axios, { AxiosInstance } from 'axios';
import { BusinessErrorResponse } from '@customTypes/response/error';
import { CommonResponse } from '~/@types/response/success';
import { RequestParam } from '~/@types/request/common';

const API_SERVER = 'http://localhost:8080';
const MEDIA_TYPE = {
  JSON: 'application/json',
};

const getHttpResponse = async <R>({ fn, url, data }): Promise<R | BusinessErrorResponse> => {
  let response;

  try {
    const { status, data: resData } = await fn(url, data);
    response = { status, data: resData };
    return response as R;
  } catch (err) {
    if (!err.response) {
      return {
        status: 500,
        message: err.message,
        code: null,
        errors: null,
      } as BusinessErrorResponse;
    }
    const { status, message, code, errors } = err.response.data;
    response = { status, message, code, errors };
    return response as BusinessErrorResponse;
  }
};

const toQueryString = (obj): string =>
  Object.entries(obj)
    .map(([k, v]) => `${k}=${v}`)
    .reduce((prev, cur) => `${prev}&${cur}`);

export class Api {
  private api: AxiosInstance;

  constructor() {
    this.api = axios.create({
      baseURL: API_SERVER,
      headers: {
        'Content-Type': MEDIA_TYPE.JSON,
        Accept: MEDIA_TYPE.JSON,
      },
      timeout: 5000,
    });
  }

  public async get<R extends CommonResponse, D = undefined>({
    url,
    data,
  }: RequestParam<D>): Promise<R | BusinessErrorResponse> {
    if (data !== undefined) {
      url += '?' + toQueryString(data);
      data = undefined;
    }
    console.log(url);
    // TODO: 아래의 경우 왜 타입 에러가 나는지 확인해봐야 함
    // const response: R = { status: 100, data: { a: 1 } };
    return getHttpResponse<R>({ fn: this.api.get, url, data });
  }

  public async post<R extends CommonResponse, D>({
    url,
    data,
  }: RequestParam<D>): Promise<R | BusinessErrorResponse> {
    return getHttpResponse<R>({ fn: this.api.post, url, data });
  }

  public async patch<R extends CommonResponse, D>({
    url,
    data,
  }: RequestParam<D>): Promise<R | BusinessErrorResponse> {
    return getHttpResponse<R>({ fn: this.api.patch, url, data });
  }

  public async delete<R extends CommonResponse, D>({
    url,
    data,
  }: RequestParam<D>): Promise<R | BusinessErrorResponse> {
    return getHttpResponse<R>({ fn: this.api.delete, url, data });
  }
}
