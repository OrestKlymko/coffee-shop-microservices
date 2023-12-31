package org.study.baristaservice.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.baristaservice.model.KafkaDto;
import org.study.baristaservice.model.Status;


@Service
@AllArgsConstructor
public class BaristaTimer{


	public KafkaDto runWithStatus(KafkaDto kafkaDto) {
		Status changeStatus = changeStatus(kafkaDto.getStatus());
		if(changeStatus!=null) {
			kafkaDto.setStatus(changeStatus);
		}
		return kafkaDto;
	}

	public Status changeStatus(Status status) {
		Status[] valuesStatus = Status.values();
		for (int i = 0; i < valuesStatus.length; i++) {
			if (valuesStatus[i].equals(status) && i < valuesStatus.length - 1) {
				return valuesStatus[i + 1];
			}
		}
		return null;
	}

}
